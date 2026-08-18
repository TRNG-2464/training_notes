/*
    This example showcases a simple TypeScript component
    There are two steps we take when using Typescript with
    component function creation
*/

// Step 1: Describe the "shape" of the props this component expects
type TsxCardProps = {
    name: string;
    age: number;
    isActive: boolean;
}
/*
    Note: The following works as well (and we will commonly use
            interfaces moving forward)
    interface TsxCardProps {
        name: string;
        age: number;
        isActive: boolean;
    }
*/

// Added this import (see 'TsxCardPropsExport.ts' file for details) just
// to showcase 'type' imports and exports more clearly
import {type TsxCardPropsExport} from './TsxCardPropsExport';

function myFunc() {
    console.log("Hello");
}

// Step 2: Apply that shape to the component's props parameter.
function TsxCard({ name, age, isActive }: TsxCardPropsExport) {
    return (
        <div>
            <h2>{name}!</h2>
            <p>Age: {age}</p>
            <p>{isActive ? 'Active' : 'Inactive'}</p>
        </div>
    );
}

/*
    Named Exports: you state the exact name of the function/interface/class/variable
    you want to export, in curly braces. Named exports allow you to consolidate all
    exports in a single block at the bottom of your module (file)
*/
export { TsxCard, myFunc, type TsxCardProps};