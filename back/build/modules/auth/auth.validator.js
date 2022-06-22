"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.locationValidators = exports.loginValidators = exports.signupValidators = void 0;
const express_validator_1 = require("express-validator");
exports.signupValidators = [
    (0, express_validator_1.check)('name').notEmpty().withMessage('field name is required'),
    (0, express_validator_1.check)('age').notEmpty().withMessage('field age is required'),
    (0, express_validator_1.check)('gender').notEmpty().withMessage('field gender is required'),
    (0, express_validator_1.check)('location').notEmpty().withMessage('field location is required'),
    (0, express_validator_1.check)('items').notEmpty().withMessage('field location is required'),
];
exports.loginValidators = [
    (0, express_validator_1.check)('name').notEmpty().withMessage('field name is required'),
];
exports.locationValidators = [
    (0, express_validator_1.check)('latitude').notEmpty().withMessage('field latitude is required'),
    (0, express_validator_1.check)('longitude').notEmpty().withMessage('field longitude is required'),
];
