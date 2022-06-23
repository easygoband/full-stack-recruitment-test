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
exports.AuthService = void 0;
const user_model_1 = require("@models/user.model");
const error_handler_1 = require("@middlewares/error_handler");
class AuthService {
    signup(user) {
        return __awaiter(this, void 0, void 0, function* () {
            const dbUser = yield user_model_1.User.findOne({ name: user.name });
            console.log(dbUser);
            if (dbUser)
                throw new error_handler_1.ErrorHandler(400, 'Name already exists');
            const newUser = new user_model_1.User(user);
            yield newUser.save();
            const token = newUser.createToken();
            newUser === null || newUser === void 0 ? void 0 : newUser.points();
            return { user: newUser, token };
        });
    }
    login(name) {
        return __awaiter(this, void 0, void 0, function* () {
            const dbUser = yield user_model_1.User.findOne({ name });
            if (!dbUser)
                throw new error_handler_1.ErrorHandler(400, 'Bad credentials');
            // const match = dbUser.comparePassword(password)
            // if (!match) throw new ErrorHandler(400, 'Bad credentials')
            dbUser === null || dbUser === void 0 ? void 0 : dbUser.points();
            const token = dbUser.createToken();
            return { user: dbUser, token };
        });
    }
    udpateLocation(latitude, longitude, userId) {
        return __awaiter(this, void 0, void 0, function* () {
            const dbUser = yield user_model_1.User.findOneAndUpdate({ _id: userId }, { location: { latitude, longitude } });
            return { success: true };
        });
    }
    userList(userId) {
        return __awaiter(this, void 0, void 0, function* () {
            let criteria = {};
            if (userId) {
                criteria = {
                    _id: { $ne: userId }
                };
            }
            const dbUserList = yield user_model_1.User.find(criteria);
            if (dbUserList && dbUserList.length > 0) {
                dbUserList.map(item => item.points());
            }
            return dbUserList;
        });
    }
    userInfo(userId) {
        return __awaiter(this, void 0, void 0, function* () {
            const userInfo = yield user_model_1.User.findOne({ _id: userId });
            userInfo === null || userInfo === void 0 ? void 0 : userInfo.points();
            return userInfo;
        });
    }
}
exports.AuthService = AuthService;
