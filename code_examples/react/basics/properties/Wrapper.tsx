/*
    React provides a built-in type, "ReactNode" which is used
    to describe anything valid that can be rendered as children
    (text, elements, numbers, arrays, etc...)

    Notice that this is a 'type' import - it can only be referenced
    as a Type, not as a true Component (i.e. it doesn't render anything)
*/
import type { ReactNode } from 'react';

// ReactNode is used as the type for the children prop, which can be any valid React element or text.
interface WrapperProps {
  children: ReactNode;
}

function Wrapper({ children }: WrapperProps) {
    return <div className="wrapper">{children}</div>;
}

export default Wrapper;

/*
    When imported and used in another component, the Wrapper can looks something like this:
    <Wrapper>
        <p>This works because ReactNode covers JSX content like this...</p>
    </Wrapper>
*/