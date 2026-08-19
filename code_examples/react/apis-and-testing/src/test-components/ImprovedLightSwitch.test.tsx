/*
  Note: This example showcases '@testing-library/jest-dom' as 
  well as '@testing-library/user-event' to enhance our tests.

  '@testing-library/jest-dom' provides the 'toBeInTheDocument()'
  matcher shown below.
  '@testing-library/jest-dom' isn't immediately obvious in this
  test file - see the 'vitest.config.ts' and 'test-setup.ts' files

  '@testing-library/user-event' provides the 'userEvent'. This provides
  a better simulation for user interaction with a page than 'fireEvent'
*/
import { render, screen, cleanup } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { describe, it, expect, afterEach } from "vitest";
import LightSwitch from "./LightSwitch";

// Although there is only a single test in this example
// we include cleanup in anticipation of more tests
afterEach(() => {
    cleanup();
})

describe("ImprovedLightSwitch", () => {
  it("toggles to ON when clicked", async () => {
    const user = userEvent.setup();
    render(<LightSwitch />);

    const button = screen.getByRole("button", { name: /flip switch/i });

    // userEvent simulates a MORE REALISTIC sequence of events
    // than fireEvent — note it's awaited, since it more closely
    // models real, asynchronous user interaction.
    await user.click(button);

    // jest-dom's matcher — reads more clearly than checking a
    // query's raw return value directly.
    expect(screen.getByText(/currently: ON/i)).toBeInTheDocument();
  });
});