/*
    This example showcases a real-world use case for uncontrolled
    compontents : File inputs

    Browsers won't let React (or any JavaScript for that matter)
    set their value directly
*/
import { useRef } from "react";

function FileUpload() {
  const fileInputRef = useRef<HTMLInputElement>(null);

  function handleUpload() {
    const file = fileInputRef.current?.files?.[0];
    if (file) {
      console.log("Selected file:", file.name);
    }
  }

  return (
    <div>
      <input type="file" ref={fileInputRef} />
      <button onClick={handleUpload}>Upload</button>
    </div>
  );
}

export default FileUpload;