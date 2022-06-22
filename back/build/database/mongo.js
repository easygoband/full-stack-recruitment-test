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
var __asyncValues = (this && this.__asyncValues) || function (o) {
    if (!Symbol.asyncIterator) throw new TypeError("Symbol.asyncIterator is not defined.");
    var m = o[Symbol.asyncIterator], i;
    return m ? m.call(o) : (o = typeof __values === "function" ? __values(o) : o[Symbol.iterator](), i = {}, verb("next"), verb("throw"), verb("return"), i[Symbol.asyncIterator] = function () { return this; }, i);
    function verb(n) { i[n] = o[n] && function (v) { return new Promise(function (resolve, reject) { v = o[n](v), settle(resolve, reject, v.done, v.value); }); }; }
    function settle(resolve, reject, d, v) { Promise.resolve(v).then(function(v) { resolve({ value: v, done: d }); }, reject); }
};
const { MongoClient, ObjectId, } = require('mongodb');
const uri = "mongodb+srv://ricardo:ricardo1234@cluster0.ohymt.mongodb.net/test";
const serviceList = ["glucose", "pressure", "weight", "water", "steps", "pulse", "sleep"];
function main() {
    var e_1, _a;
    return __awaiter(this, void 0, void 0, function* () {
        console.log('Init');
        const client = new MongoClient(uri);
        yield client.connect();
        const dbService = client.db("red-salud-patients").collection("rpm.reports");
        dbService.deleteMany({
            "patientId": ObjectId(patientId)
        });
        try {
            try {
                for (var serviceList_1 = __asyncValues(serviceList), serviceList_1_1; serviceList_1_1 = yield serviceList_1.next(), !serviceList_1_1.done;) {
                    const item = serviceList_1_1.value;
                    const dataList = fnCreatePayloadList(item);
                    // if (item == 'glucose') {
                    // }
                    // console.log(dataList);
                    console.log(item);
                    yield dbService.insertMany(dataList).then(res => console.log(`Res rpm/${item}`, res.result)).catch(error => console.log(`Error rpm/${item}`, error));
                }
            }
            catch (e_1_1) { e_1 = { error: e_1_1 }; }
            finally {
                try {
                    if (serviceList_1_1 && !serviceList_1_1.done && (_a = serviceList_1.return)) yield _a.call(serviceList_1);
                }
                finally { if (e_1) throw e_1.error; }
            }
        }
        catch (error) {
            console.log('ERROR: ', error);
        }
        client.close();
    });
}
function listDatabases(client) {
    return __awaiter(this, void 0, void 0, function* () {
        const databasesList = yield client.db().admin().listDatabases();
        console.log("Databases:");
        databasesList.databases.forEach(db => console.log(` - ${db.name}`));
    });
}
;
