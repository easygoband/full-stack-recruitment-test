"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.mongo = void 0;
const mongoose_1 = __importDefault(require("mongoose"));
const settings_1 = require("@config/settings");
exports.mongo = mongoose_1.default
    .connect(settings_1.settings.DB.URI)
    .then((db) => console.log('Mongo is online...'))
    .catch((error) => console.error(error));
