/*
    This file defines the fake responses MSW should return
    for specific requests
*/
import { http, HttpResponse } from "msw";

// Represents the mocked, raw, json response for a specific URL
const mockBulbasaurResponse = {
  id: 1,
  name: "bulbasaur",
  height: 7,
  weight: 69,
  types: [
    { slot: 1, type: { name: "grass", url: "" } },
    { slot: 2, type: { name: "poison", url: "" } },
  ],
  sprites: {
    front_default: "https://example.com/bulbasaur.png",
    front_shiny: "https://example.com/bulbasaur-shiny.png",
  },
};

const mockHitmonchanResponse = {
  id: 107,
  name: "hitmonchan",
  height: 14,
  weight: 502,
  types: [{ slot: 1, type: { name: "fighting", url: "" } }],
  sprites: {
    front_default: "https://example.com/hitmonchan.png",
    front_shiny: "https://example.com/hitmonchan-shiny.png",
  },
};

/* 
    List of handlers to pass to setupServer - all handlers are 
    spread (...) so the server knows what mocked object should
    be returned for each different URL called
*/
const handlers = [
  // Whenever a GET request is made matching this URL pattern,
  // respond with mockBulbasaurResponse instead of hitting PokeAPI.
  http.get("https://pokeapi.co/api/v2/pokemon/bulbasaur", () => {
    return HttpResponse.json(mockBulbasaurResponse);
  }),

  /* 
    This second handler is using an async function to set a delay.
    This is simulating a slower network response for the test
  */
  http.get(
    "https://pokeapi.co/api/v2/pokemon/hitmonchan",
    async () => {
      // A small, artificial delay — useful for testing loading
      // states, or simply demonstrating that handlers can be
      // async and simulate real-world latency.
      await new Promise((resolve) => setTimeout(resolve, 200));
      return HttpResponse.json(mockHitmonchanResponse);
    }
  ),
];

export { handlers, mockBulbasaurResponse, mockHitmonchanResponse };