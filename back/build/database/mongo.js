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
const { MongoClient, ObjectId, } = require('mongodb');
const uri = "mongodb+srv://ricardo:ricardo1234@cluster0.ohymt.mongodb.net/test";
const client = new MongoClient(uri);
// await client.connect();
const dbService = client.db("red-salud-patients").collection("rpm.reports");
// dbService.deleteMany({
// 	"patientId": ObjectId(patientId)
// })
// client.close() 
function listDatabases(client) {
    return __awaiter(this, void 0, void 0, function* () {
        const databasesList = yield client.db().admin().listDatabases();
        console.log("Databases:");
        databasesList.databases.forEach(db => console.log(` - ${db.name}`));
    });
}
;
