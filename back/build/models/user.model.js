"use strict";
var __rest = (this && this.__rest) || function (s, e) {
    var t = {};
    for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0)
        t[p] = s[p];
    if (s != null && typeof Object.getOwnPropertySymbols === "function")
        for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
            if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i]))
                t[p[i]] = s[p[i]];
        }
    return t;
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.User = void 0;
const mongoose_1 = __importDefault(require("mongoose"));
const bcrypt_1 = __importDefault(require("bcrypt"));
const settings_1 = require("@config/settings");
const jsonwebtoken_1 = __importDefault(require("jsonwebtoken"));
const LocationSchema = new mongoose_1.default.Schema({
    latitude: { type: String, required: true },
    longitude: { type: String, required: true },
});
const ItemsSchema = new mongoose_1.default.Schema({
    water: { type: Number, required: true },
    food: { type: Number, required: true },
    medication: { type: Number, required: true },
    ammunition: { type: Number, required: true },
});
const UserIdsSchema = new mongoose_1.default.Schema({
    user: { type: String },
    _id: { type: String },
});
const UserSchema = new mongoose_1.default.Schema({
    age: { type: Number, required: true },
    itemsPoints: { type: Number },
    name: { type: String, required: true, trim: true },
    gender: { type: String, required: true },
    infected: { type: Boolean, default: false },
    reports: { type: [UserIdsSchema] },
    location: LocationSchema,
    items: ItemsSchema,
    created_at: { type: Date, default: new Date() },
    updated_at: { type: Date, default: new Date() },
});
UserSchema.methods.toJSON = function () {
    const user = this.toObject();
    const { __v, password } = user, rest = __rest(user, ["__v", "password"]);
    return rest;
};
UserSchema.methods.createToken = function () {
    const user = this.toObject();
    return jsonwebtoken_1.default.sign({ _id: user._id }, settings_1.settings.SECRET, {
        expiresIn: 86400,
    });
};
UserSchema.methods.encryptPassword = function (password) {
    return bcrypt_1.default.hashSync(password, bcrypt_1.default.genSaltSync(10));
};
UserSchema.methods.comparePassword = function (password) {
    let user = this.toObject();
    return bcrypt_1.default.compareSync(password, user.password);
};
UserSchema.methods.points = function () {
    let user = this.toObject();
    let total = 0;
    if (user.items) {
        total += user.items.water * 4;
        total += user.items.food * 4;
        total += user.items.medication * 4;
        total += user.items.ammunition * 4;
    }
    this.to;
    this.itemsPoints = total;
};
mongoose_1.default.connect(settings_1.settings.DB.URI);
exports.User = mongoose_1.default.model('User', UserSchema);
