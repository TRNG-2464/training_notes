/*
    This test file would typically be placed in the same directory
    as the module it is testing ('/responses').

    I have placed it here with other mock file, purely for organizational
    purposes for your notes.
*/
import { describe, it, expect, beforeAll, afterEach, afterAll } from "vitest";
import { http, HttpResponse } from "msw";
import { server } from "./server-setup";
import { getPokemonFetch } from "../responses/responseService";

/*
  Setup & Teardown code for tests
*/
// setup server with .listen() - starts interception process
beforeAll(() => server.listen());

// undo any per-test overrides (if any)
afterEach(() => server.resetHandlers());

// close server once all tests have concluded
afterAll(() => server.close());

describe("Testing getPokemonFetch() function from responseService.ts", () => {
  it("Testing function shapes a successful response into the Pokemon interface correctly", async () => {
    // Uses the DEFAULT handler from mocks/handlers.ts — no
    // per-test override needed for the "happy path."
    const result = await getPokemonFetch("bulbasaur");

    expect(result).toEqual({
      id: 1,
      name: "bulbasaur",
      height: "70 cm",
      weight: "6.9 kg",
      types: ["grass", "poison"],
      spriteUrl: "https://example.com/bulbasaur.png",
    });
  });

  it("Testing function throws a clear error when the response status is not ok", async () => {
    // Overriding the handler for THIS TEST ONLY — simulating a
    // 404, without touching the shared, default handler.
    server.use(
      http.get("https://pokeapi.co/api/v2/pokemon/not-a-real-pokemon", () => {
        return new HttpResponse(null, { status: 404 });
      })
    );

    await expect(getPokemonFetch("not-a-real-pokemon")).rejects.toThrow(
      "Pokémon not found (Status: 404)"
    );
  });


  /*
    This example showcases a 'happy path result' using server.use instead. 
    We can also use this 'server.use' function to to 'call tracking' i.e.
    checking other details about a request that was made, such as 'did we
    ping the correct endpoint?' - this might be useful if you used some test
    endpoint during development, but forgot to change it back
  */
  it("Testing function actually calls the correct endpoint when searching for eevee (call tracking)", async () => {
    let requestWasMade = false; // flag to determine if the request was actually sent
    let requestedUrl = "";      // string to see which URL the code actually pings. This will determine
                                // if we have a potentially hard-coded value or other bug somewhere

    server.use(
      http.get("https://pokeapi.co/api/v2/pokemon/eevee", ({ request }) => {
        requestWasMade = true;      // After the request was made, we change the flag to true
        requestedUrl = request.url; // Check which URL was actually pinged

        // We respond with this mock data
        return HttpResponse.json({
          id: 133,
          name: "eevee",
          height: 3,
          weight: 65,
          types: [{ slot: 1, type: { name: "normal", url: "" } }],
          sprites: {
            front_default: "https://example.com/eevee.png",
            front_shiny: "https://example.com/eevee-shiny.png",
          },
        });
      })
    );

    const result = await getPokemonFetch("eevee");  // Now, we call our function hoping for the 'eevee' endpoint (above)

    // Confirming our code actually MADE the request, and hit
    // the correct URL — not just that it handled the response
    // correctly.
    expect(requestWasMade).toBe(true);  // if the 'eevee' endpoint was actually pinged, this should be true
    expect(requestedUrl).toBe("https://pokeapi.co/api/v2/pokemon/eevee"); // This should be the actual URL pinged
    expect(result.name).toBe("eevee");  // This should match the name of our mocked, returned data
  });

  // Notice that this handler is asynchronous - so they can be used to check real
  // world network delays
  it("Testing function resolves correctly even with a delayed response (hitmonchan)", async () => {
    const result = await getPokemonFetch("hitmonchan");

    expect(result).toEqual({
      id: 107,
      name: "hitmonchan",
      height: "140 cm",
      weight: "50.2 kg",
      types: ["fighting"],
      spriteUrl: "https://example.com/hitmonchan.png",
    });
  });
});