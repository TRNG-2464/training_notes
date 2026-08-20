/*
    Simple Higher-Order Component

    It takes any component and returns a new component
    that renders the original, but wrapped inside a border
*/
function withBorder(WrapperComponent) {
    function ComponentWithBorder(props) {
        return (
            <div style={{ border: "2px solid block", padding: "10px"}}>
                <WrapperComponent {...props} />
            </div>
        );
    }

    return ComponentWithBorder;
}

export default withBorder;