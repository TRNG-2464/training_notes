/*
    This showcases a simple component using just JavaScript
    Note, this does not contain any Type-checking or other
    advantages of TypeScript
*/
function JsxCard(props) {
    return (
        <div className="card">
            <h2>{props.name}</h2>
            <p>Age: {props.age}</p>
            <p>{props.isActive ? "Active" : "Inactive"}</p>
        </div>
    );
}

/*
    Each module (file) can only have a single default export. Typically
    When using default exports, the module will only contain the single
    entity which should be exported
*/
export default JsxCard;

