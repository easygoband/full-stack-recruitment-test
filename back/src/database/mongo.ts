const {
	MongoClient,
	ObjectId,
} = require('mongodb');
const uri = "mongodb+srv://ricardo:ricardo1234@cluster0.ohymt.mongodb.net/test";
 
const serviceList = ["glucose", "pressure", "weight", "water", "steps", "pulse", "sleep"];


async function main() {
	console.log('Init');
	const client = new MongoClient(uri);
	await client.connect();
	const dbService = client.db("red-salud-patients").collection("rpm.reports");
	dbService.deleteMany({
		"patientId": ObjectId(patientId)
	})

	try {
		for await (const item of serviceList) {
			const dataList = fnCreatePayloadList(item);
			// if (item == 'glucose') {
			// }
			// console.log(dataList);
			console.log(item);
			await dbService.insertMany(dataList).then(res => console.log(`Res rpm/${item}`, res.result)).catch(error => console.log(`Error rpm/${item}`, error));
		}
	} catch (error) {
		console.log('ERROR: ', error);
	}

	client.close() 
}

async function listDatabases(client) {
	const databasesList = await client.db().admin().listDatabases();
	console.log("Databases:");
	databasesList.databases.forEach(db => console.log(` - ${db.name}`));
};

 