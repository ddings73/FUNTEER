import React from 'react';
import CKEditor from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
/* eslint-disable-next-line react/react-in-jsx-scope */
export function RichTextEditor() {
  return (
    <CKEditor
      editor={ClassicEditor}
      data="<p>Hello from CKEditor 5!</p>"
      onInit={(editor: any) => {
        // You can store the "editor" and use when it is needed.
        /* eslint-disable */
        console.log('Editor is ready to use!', editor);
      }}
      onChange={(event: any, editor: any) => {
        const data = editor.getData();
        console.log({ event, editor, data });
      }}
      onBlur={(event: any, editor: any) => {
        console.log('Blur.', editor);
      }}
      onFocus={(event: any, editor: any) => {
        console.log('Focus.', editor);
      }}
    />
  );
}
