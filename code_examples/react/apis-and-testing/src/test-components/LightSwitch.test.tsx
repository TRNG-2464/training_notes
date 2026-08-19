/*
    This file includes tests on a simple Component with state
    and rendered JSX
*/
import { render, screen, fireEvent, cleanup } from "@testing-library/react";
import { describe, it, expect, afterEach } from "vitest";
import LightSwitch from "./LightSwitch"; 

// cleanup will unmount components after each test run
// without this clean-up step, 'render' will mount multiple
// components, and may potentially throw off test results.
afterEach(() => {
    cleanup();
})

describe("LightSwitch", () => {
  it("renders with the light off by default", () => {
    render(<LightSwitch />);  // Note: since both tests in this file render the same component
                              // I could put this render call in a 'beforeEach' setup function

    // This confirms that the test 'currently: OFF' is the 
    // default text, i.e. the component is off
    const status = screen.getByText(/currently: OFF/i);

    expect(status).toBeDefined();
  });


  /*
    The following test uses fireEvent to simulate user
    interactions with our component
   */
    it("toggles to ON when the button is clicked", () => {
      render(<LightSwitch />);

      // Querying by ROLE — recognizing this element as a button,
      // the same way a user (or assistive technology) would.
      const button = screen.getByRole("button", { name: /flip switch/i });

      // Simulating a real user's click.
      fireEvent.click(button);

      // Confirming the UI actually updated in response.
      const status = screen.getByText(/currently: ON/i);
      expect(status).toBeDefined();
  });
});