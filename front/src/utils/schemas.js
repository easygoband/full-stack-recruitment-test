import {
    object,
    string,
    number,
    date,
    boolean,
    array,
    setLocale,
    ref
} from 'yup';

import i18n from '@/plugins/i18n'

setLocale({
    mixed: {
        default: i18n.global.t('validations.required'),
        required: ({value}) =>  {
            return i18n.global.t('validations.required')
        },
        array: ({value}) =>  {
            return i18n.global.t('validations.required')
        },
    },
    string: {
        email: ({value,email}) =>  {
            return i18n.global.t('validations.email')
        },
        min: ({value,min}) =>  {
            return i18n.global.t('validations.min', { min })
        },
        max: ({value,max}) =>  {
            return i18n.global.t('validations.max', { max })
        },
        required: ({value}) =>  {
            return i18n.global.t('validations.required')
        },
    },
    number: {
        min: ({value,min}) =>  {
            return i18n.global.t('validations.minValue', { min })
        },
        max: ({value,max}) =>  {
            return i18n.global.t('validations.maxValue', { max })
        },
        required: ({value}) =>  {
            return i18n.global.t('validations.required')
        },
    },
    array: {
        min: ({value,min}) =>  {
            return i18n.global.t('validations.required')
        },
    }
  });

  

export const LoginSchema = object({
    email: string().email().required(),
    password: string().min(8).required(),
});

export const PasswordRecoverySchema = object({
    email: string().email().required(),
});

export const PasswordResetSchema = object({
    password: string().min(8).required(),
    confirmPassword: string().oneOf([ref('password'), null], i18n.global.t('validations.mustMatch')).min(8).required()
});


export const AddMedicineSchema = object({
    name: string().required().min(2),
    dose: number().typeError(i18n.global.t('validations.required')).required().min(1),
    unit: string().required(),
    instructions: string().min(5),
    frequency: number().required(),
    duration: number().required(),
    expiration: boolean(),
});

export const AllergySchema = object({
    substance: string().min(3).required(),
    category: string().required(),
    // type: array().required().min(1),
    type: array()
    .min(1)
    .required()
    .nullable(),
});

export const invitePatientSchema = object({
    email: string().email().required(),
    phone: number().typeError(i18n.global.t('validations.required')).required().min(1000000000).max(9999999999),
    message: string().min(3).required()
});