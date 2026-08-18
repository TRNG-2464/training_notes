import { useState } from "react";

function ExampleComponent() {
    const [value, setValue] = useState("");

    return <input value={value} onChange={(e) => setValue(e.target.value)} />;
}

export default ExampleComponent;