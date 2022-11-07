import React, { useState, useEffect } from 'react';

import { urlUtil } from '@grafana/data';
import { config, featureEnabled, reportInteraction } from '@grafana/runtime';
import { ModalTabContent, Button, LinkButton, RadioButtonGroup, Field, Modal } from '@grafana/ui';
import { ShareModalTabProps } from 'app/features/dashboard/components/ShareModal';
import { getVariablesByKey } from 'app/features/variables/state/selectors';

import { ReportOrientation, ReportLayout, reportOrientations, reportLayouts, ReportDashboard } from '../types';
import { getRootPath } from '../utils/url';

import { NoRendererInfoBox } from './RenderingWarnings';
import { UnavailableFeatureInfoBox } from './UnavailableFeatureInfoBox';
import { getTimeRangeFromURL } from './utils/url';
import { variablesToCsv } from './utils/variables';

export const SharePDF = ({ dashboard, onDismiss }: ShareModalTabProps) => {
  useEffect(() => {
    reportInteraction('grafana_dashboards_pdf_share_viewed');
  }, []);
  const [orientation, setOrientation] = useState<ReportOrientation>('landscape');
  const [layout, setLayout] = useState<ReportLayout>('grid');

  const buildPdfLink = () => {
    let pdfUrl = `${getRootPath()}/api/reports/render/pdfs`;
    const params: Record<string, string> = { orientation, layout };
    const variables = getVariablesByKey(dashboard.uid);
    const reportDashboard: {
      dashboard: Partial<ReportDashboard['dashboard']>;
      timeRange?: ReportDashboard['timeRange'];
      reportVariables?: ReportDashboard['reportVariables'];
    } = {
      dashboard: {
        uid: dashboard.uid,
      },
    };
    if (variables.length) {
      reportDashboard.reportVariables = variablesToCsv(variables);
    }
    const timeRange = getTimeRangeFromURL();

    if (timeRange) {
      reportDashboard.timeRange = timeRange;
    }

    params.dashboards = JSON.stringify([reportDashboard]);
    pdfUrl = urlUtil.appendQueryToUrl(pdfUrl, urlUtil.toUrlParams(params));

    return pdfUrl;
  };

  const onOrientationChange = (orientation: ReportOrientation) => {
    setOrientation(orientation);
  };

  const onLayoutChange = (layout: ReportLayout) => {
    setLayout(layout);
  };

  if (!config.rendererAvailable) {
    return <NoRendererInfoBox />;
  }

  if (!featureEnabled('reports.pdf')) {
    return (
      <UnavailableFeatureInfoBox
        message="Rendering a dashboard as a PDF document is not available with your current license.
            To enable this feature, update your license."
      />
    );
  }

  if (dashboard.isSnapshot()) {
    return <p className="share-modal-info-text">Sharing a PDF from a snapshot is not supported.</p>;
  }

  return (
    <ModalTabContent icon="file-alt">
      <p className="share-modal-info-text">Save the dashboard as a PDF document.</p>
      <Field label="Orientation">
        <RadioButtonGroup options={reportOrientations} value={orientation} onChange={onOrientationChange} />
      </Field>
      <Field label="Layout">
        <RadioButtonGroup options={reportLayouts} value={layout} onChange={onLayoutChange} />
      </Field>
      <Modal.ButtonRow>
        <Button variant="secondary" onClick={onDismiss}>
          Cancel
        </Button>
        <LinkButton variant="primary" href={buildPdfLink()} target="_blank" rel="noreferrer noopener">
          Save as PDF
        </LinkButton>
      </Modal.ButtonRow>
    </ModalTabContent>
  );
};
