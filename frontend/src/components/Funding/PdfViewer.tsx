/* eslint-disable*/
import { defaultLayoutPlugin } from '@react-pdf-viewer/default-layout';
import React, { useState } from 'react';
import { Document, Page, pdfjs } from 'react-pdf';
import styles from './PdfViewer.module.scss';

pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;

interface Props {
  pdfUrl: string;
}

const PdfViewer: React.FC<Props> = ({ pdfUrl }) => {
  const [numPages, setNumPages] = useState<number | null>(null);

  function onDocumentLoadSuccess({ numPages }: { numPages: number }) {
    setNumPages(numPages);
  }

  return (
    <div>
      <Document file={pdfUrl} onLoadSuccess={onDocumentLoadSuccess} onLoadError={console.error}>
        {Array.from(new Array(numPages || 0), (el, index) => (
          <Page key={`page_${index + 1}`} pageNumber={index + 1} />
        ))}
      </Document>
    </div>
  );
};

export default PdfViewer;
