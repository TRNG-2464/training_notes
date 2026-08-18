import { useState } from "react";

function RuleOne() {
  // CORRECT — both Hooks are called unconditionally,
  // at the top level, in the same order every render.
  const [name, setName] = useState("Rule One | Initial Value");
  const [age, setAge] = useState(100);

  return (
    <p>{name}, {age}</p>
  );
}

function BadExample({ shouldTrack }: { shouldTrack: boolean }) {
  /* The following is INCORRECT usage! - Don't do this
        Calling a Hook inside a conditional means it might run on
        some renders and not others, breaking React's ability to
        consistently track this piece of state.
  */
  if (shouldTrack) {
    const [count, setCount] = useState(0);
  }

  return <p>Example</p>;
}

export default RuleOne;