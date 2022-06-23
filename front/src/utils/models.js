export const _UserModel = () => {
    return {
        _id: null,
        location: {
            latitude: null,
            longitude: null,
        },
        items: {
            water: 0,
            food: 0,
            medication: 0,
            ammunition: 0,
        }
    }
}

export const _ReportModel = () => {

    return {
        users: {
            points: 0,
            total: 0,
            water: 0,
            food: 0,
            medication: 0,
            ammunition: 0,
        },
        infected: {
            points: 0,
            total: 0,
            water: 0,
            food: 0,
            medication: 0,
            ammunition: 0,
        }
    }
}