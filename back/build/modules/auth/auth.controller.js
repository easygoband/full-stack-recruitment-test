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
Object.defineProperty(exports, "__esModule", { value: true });
exports.usersReports = exports.userInfected = exports.userInfo = exports.userList = exports.udpateLocation = exports.login = exports.signup = void 0;
const services_1 = require("./services");
function signup(req, res, next) {
    return __awaiter(this, void 0, void 0, function* () {
        const authService = new services_1.AuthService();
        const body = req.body;
        try {
            const user = yield authService.signup(body);
            res.json(user);
        }
        catch (error) {
            next(error);
        }
    });
}
exports.signup = signup;
function login(req, res, next) {
    return __awaiter(this, void 0, void 0, function* () {
        const authService = new services_1.AuthService();
        const body = req.body;
        try {
            const user = yield authService.login(body.name);
            res.json(user);
        }
        catch (error) {
            next(error);
        }
    });
}
exports.login = login;
function udpateLocation(req, res, next) {
    return __awaiter(this, void 0, void 0, function* () {
        const authService = new services_1.AuthService();
        const { latitude, longitude } = req.body;
        try {
            const user = yield authService.udpateLocation(latitude, longitude, req.user._id);
            res.json(user);
        }
        catch (error) {
            next(error);
        }
    });
}
exports.udpateLocation = udpateLocation;
function userList(req, res, next) {
    return __awaiter(this, void 0, void 0, function* () {
        const authService = new services_1.AuthService();
        try {
            const userList = yield authService.userList(req.query.userId);
            res.json(userList);
        }
        catch (error) {
            next(error);
        }
    });
}
exports.userList = userList;
function userInfo(req, res, next) {
    return __awaiter(this, void 0, void 0, function* () {
        const authService = new services_1.AuthService();
        try {
            const user = yield authService.userInfo(req.params.userId);
            res.json(user);
        }
        catch (error) {
            next(error);
        }
    });
}
exports.userInfo = userInfo;
function userInfected(req, res, next) {
    return __awaiter(this, void 0, void 0, function* () {
        const authService = new services_1.AuthService();
        try {
            const user = yield authService.userInfected(req.params.userId, req.user._id);
            res.json(user);
        }
        catch (error) {
            next(error);
        }
    });
}
exports.userInfected = userInfected;
function usersReports(req, res, next) {
    return __awaiter(this, void 0, void 0, function* () {
        const authService = new services_1.AuthService();
        try {
            const report = yield authService.usersReports();
            res.json(report);
        }
        catch (error) {
            next(error);
        }
    });
}
exports.usersReports = usersReports;
