export const _MedicationList = () => {
    return {
        units: [{
                type: 'tab',
                label: 'Tableta (s)'
            },
            {
                type: 'vial',
                label: 'Ampolleta'
            },
            {
                type: 'cap',
                label: 'Cápsula'
            },
            {
                type: 'tablets',
                label: 'Pastilla'
            },
            {
                type: 'tablespoon',
                label: 'Cucharada'
            },
            {
                type: 'drop',
                label: 'Gota'
            },
            {
                type: 'ml',
                label: 'ml'
            },
        ],
        frequency: [{
                type: 4,
                label: 'Cada 4 horas'
            },
            {
                type: 6,
                label: 'Cada 6 horas'
            },
            {
                type: 8,
                label: 'Cada 8 horas'
            },
            {
                type: 12,
                label: 'Cada 12 horas'
            },
            {
                type: 24,
                label: 'Cada 24 horas'
            },
        ],
        duration: [{
                type: 3,
                label: '3 días'
            },
            {
                type: 5,
                label: '5 días'
            },
            {
                type: 7,
                label: '7 días'
            },
            {
                type: 10,
                label: '10 días'
            },
            {
                type: 15,
                label: '15 días'
            },
            {
                type: 30,
                label: '30 días'
            },
        ]
    }
}

export const _AllergiesList = () => {

    return {
        types: [{
                type: 'hives',
                label: 'Ronchas y/o Picazón'
            },
            {
                type: 'nasal',
                label: 'Congestión nasal'
            },
            {
                type: 'skin',
                label: 'Erupciones cutáneas'
            },
            {
                type: 'eyes',
                label: 'Ojos rojos y llorosos'
            },
            {
                type: 'respiratory',
                label: 'Dificultad respiratoria'
            },
            {
                type: 'cough',
                label: 'Tos'
            },
            {
                type: 'nausea',
                label: 'Náuseas y vómitos'
            },
            {
                type: 'swelling',
                label: 'Hinchazón del rostro, los ojos o la lengua'
            },
            {
                type: 'other',
                label: 'Otra'
            },
        ],
        categories: [{
                type: 'hair',
                label: 'Caspa y / o pelo de animales'
            },
            {
                type: 'dust',
                label: 'Polvo'
            },
            {
                type: 'medications',
                label: 'Medicamentos'
            },
            {
                type: 'food',
                label: 'Alimentos'
            },
            {
                type: 'poison',
                label: 'Picaduras y mordeduras de insectos(su veneno)'
            },
            {
                type: 'latex',
                label: 'Látex'
            },
            {
                type: 'pollen',
                label: 'Polen'
            },
            {
                type: 'other',
                label: 'Otro'
            },
        ],
    }
}
export const _PatientInformation = () => {
    return {
        _id: null,
       email: null,
       birthDate: null,
       firstName: null,
       lastName: null,
       gender: null,
       fullName: null,
       gender: null,
       rpmInitial: {
            bodyWeight: {
               kilograms: null,
               pound: null
            },
            bodyHeight: {
                centimeters: null,
                foots: null
            },
            physicalActivity: null,
            condition: [],
            bmi: null
       }
    }
}
