import React from 'react';
import { useForm } from 'react-hook-form';
import { connect, ConnectedProps } from 'react-redux';

import { AppEvents, urlUtil } from '@grafana/data';
import { featureEnabled, reportInteraction } from '@grafana/runtime';
import { Checkbox, Field, FieldSet, LinkButton, RadioButtonGroup, InputControl, VerticalGroup } from '@grafana/ui';
import { appEvents } from 'app/core/core';

import {
  EnterpriseStoreState,
  ReportFormData,
  ReportFormat,
  ReportLayout,
  reportLayouts,
  reportOrientations,
  StepKey,
} from '../../types';
import { getRange } from '../../utils/time';
import { updateReportProp } from '../state/reducers';
import { dashboardsInvalid } from '../utils/dashboards';
import { canEditReport } from '../utils/permissions';
import { getRendererMajorVersion } from '../utils/renderer';

import ReportForm from './ReportForm';

type FormatData = Pick<ReportFormData, 'formats' | 'options'>;

const mapStateToProps = (state: EnterpriseStoreState) => {
  const { report } = state.reports;
  return {
    report,
  };
};

const mapActionsToProps = {
  updateReportProp,
};

const connector = connect(mapStateToProps, mapActionsToProps);
export type Props = ConnectedProps<typeof connector> & { reportId?: string };

const descriptions = new Map<ReportLayout, string>([
  ['grid', 'Display the panels in their positions on the dashboard.'],
  ['simple', 'Display one panel per row.'],
]);

const formatMap = new Map<ReportFormat, string>([
  ['pdf', 'Attach the report as a PDF'],
  ['image', 'Embed a dashboard image in the email'],
  ['csv', 'Attach a CSV file of table panel data'],
]);

export const FormatReport = ({ report, updateReportProp, reportId }: Props) => {
  const { dashboards, formats, options, name } = report || {};
  const {
    handleSubmit,
    control,
    register,
    watch,
    getValues,
    formState: { isDirty },
  } = useForm({
    defaultValues: {
      formats,
      options: { layout: options.layout, orientation: options.orientation },
    },
  });
  const watchLayout = watch('options.layout');
  const watchOrientation = watch('options.orientation');
  const watchFormats = watch('formats');
  const rendererMajorVersion = getRendererMajorVersion();
  const previewEnabled = featureEnabled('reports.email') && !dashboardsInvalid(report.dashboards);

  const saveData = ({ formats, options }: FormatData) => {
    if (isDirty) {
      updateReportProp({ ...report, options: { ...report.options, ...options }, formats });
    }
  };

  const getFormData = () => {
    const { formats, options } = getValues();

    return { options: { ...report.options, ...options }, formats };
  };

  const getPreviewPDFUrl = () => {
    if (dashboardsInvalid(dashboards)) {
      return undefined;
    }

    const params: any = {
      title: name,
    };

    if (watchOrientation) {
      params.orientation = watchOrientation;
    }

    if (watchLayout) {
      params.layout = watchLayout;
    }

    params.dashboards = JSON.stringify(
      dashboards.map((db) => {
        const { from, to } = getRange(db.timeRange).raw;
        return {
          dashboard: { uid: db.dashboard?.uid },
          timeRange: { from: from.valueOf().toString(), to: to.valueOf().toString() },
          reportVariables: db.reportVariables,
        };
      })
    );

    return urlUtil.appendQueryToUrl(`api/reports/render/pdfs/`, urlUtil.toUrlParams(params));
  };

  return (
    <ReportForm
      activeStep={StepKey.FormatReport}
      onSubmit={handleSubmit(saveData)}
      confirmRedirect={isDirty}
      getFormData={getFormData}
      reportId={reportId}
    >
      <FieldSet label={'2. Format report'} disabled={!canEditReport}>
        <FieldSet>
          <VerticalGroup>
            {[...formatMap].map(([name, label]) => {
              // Get onChange from the register, to be able to customise checkbox onChange
              const { onChange: onFormatChange, ...formatFields } = register('formats');
              return (
                <Checkbox
                  {...formatFields}
                  key={name}
                  htmlValue={name}
                  label={label}
                  onChange={(val) => {
                    if (name === 'csv') {
                      const enabled = val.currentTarget.checked;
                      if (enabled && rendererMajorVersion !== null && rendererMajorVersion < 3) {
                        appEvents.emit(AppEvents.alertError, [
                          'To export CSV files, you must update the Grafana Image Renderer plugin.',
                        ]);
                      }
                    }
                    onFormatChange(val);
                  }}
                />
              );
            })}
          </VerticalGroup>
        </FieldSet>
        {watchFormats.includes('pdf') && (
          <FieldSet label={'Style the PDF'}>
            <Field label="Orientation">
              <InputControl
                name={'options.orientation'}
                control={control}
                render={({ field: { ref, ...field } }) => {
                  return <RadioButtonGroup {...field} options={reportOrientations} size={'md'} />;
                }}
              />
            </Field>
            <Field label="Layout" description={descriptions.get(watchLayout)}>
              <InputControl
                name={'options.layout'}
                control={control}
                render={({ field: { ref, ...field } }) => {
                  return <RadioButtonGroup {...field} options={reportLayouts} size={'md'} />;
                }}
              />
            </Field>
            <Field disabled={!previewEnabled}>
              <LinkButton
                onClick={() => previewEnabled && reportInteraction('reports_preview_pdf')}
                icon={'external-link-alt'}
                href={getPreviewPDFUrl()}
                size="xs"
                target="_blank"
                rel="noreferrer noopener"
                variant="secondary"
              >
                Preview PDF
              </LinkButton>
            </Field>
          </FieldSet>
        )}
      </FieldSet>
    </ReportForm>
  );
};

export default connector(FormatReport);
