import { defineConfig } from "vitest/config";

export default defineConfig({
    test: {
        environment: "jsdom",
        setupFiles: "./test-setup.ts"   // setUp Files is used for '@testing-library/jest-dom'
    },
});