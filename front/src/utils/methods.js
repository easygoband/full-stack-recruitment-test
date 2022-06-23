
export const GET_GENDER = (gender) => {
    switch(gender){
        case 'M':
            return 'male';
        case 'F':
            return 'female';
        case 'N':
            return 'nonBinary';
        default :
            return 'nonBinary';
    }
}

export const CURRENT_LOCATION = ( ) => {
    return new Promise(  ( resolve , reject )=>{
        const options = {
            enableHighAccuracy: true,
            timeout: 5000,
            maximumAge: 0
        };
        const success = (pos) => {
            resolve( pos.coords )
        };
        const error = (err) => {
            reject(err.code)
        };
        navigator.geolocation.getCurrentPosition(success, error, options);
    })
}
