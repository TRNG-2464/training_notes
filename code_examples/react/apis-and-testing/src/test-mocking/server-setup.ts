import { setupServer } from "msw/node";
import { handlers } from "./handlers";

const server = setupServer(...handlers);

// We are splitting this logic out here, only because it will help
// organize our code (if we wanted any additional setup, we could
// do so in this module...)
export { server };