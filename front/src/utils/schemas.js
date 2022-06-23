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

export const NewUserSchema = object({
    name: string().min(3).required(),
    age: number().required(),
    gender: string().required(),
    latitude: string().required(),
    longitude: string().required(),
    water: number().min(0).required(),
    food: number().min(0).required(),
    medication: number().min(0).required(),
    ammunition: number().min(0).required(),
});

export const PasswordRecoverySchema = object({
    email: string().email().required(),
});

export const PasswordResetSchema = object({
    password: string().min(8).required(),
    confirmPassword: string().oneOf([ref('password'), null], i18n.global.t('validations.mustMatch')).min(8).required()
});
