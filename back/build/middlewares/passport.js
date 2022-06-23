"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.Auth = exports.JWTStrategy = void 0;
const passport_jwt_1 = require("passport-jwt");
const settings_1 = require("@config/settings");
const user_model_1 = require("@models/user.model");
const passport_1 = __importDefault(require("passport"));
exports.JWTStrategy = new passport_jwt_1.Strategy({
    jwtFromRequest: passport_jwt_1.ExtractJwt.fromAuthHeaderAsBearerToken(),
    secretOrKey: settings_1.settings.SECRET,
}, (payload, done) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const user = yield user_model_1.User.findOne({ _id: payload._id });
        if (user)
            return done(null, user);
        return done(null, false);
    }
    catch (error) {
        done(error, false);
    }
}));
passport_1.default.use(exports.JWTStrategy);
exports.Auth = {
    initialize: function () {
        return passport_1.default.initialize();
    },
    authenticate: function (req, res, next) {
        return passport_1.default.authenticate("jwt", {
            session: false
        }, (err, user, info) => {
            if (err) {
                return next(err);
            }
            if (!user) {
                return res.json({
                    status: 'error',
                    error: 'ANOTHORIZED_USER'
                });
            }
            // Forward user information to the next middleware
            req.user = user;
            next();
        })(req, res, next);
    }
};
