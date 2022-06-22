import { check } from 'express-validator'

export const signupValidators = [
  check('name').notEmpty().withMessage('field name is required'),
  check('age').notEmpty().withMessage('field age is required'),
  check('gender').notEmpty().withMessage('field gender is required'),
  check('location').notEmpty().withMessage('field location is required'),
  check('items').notEmpty().withMessage('field location is required'),
]

export const loginValidators = [
  check('name').notEmpty().withMessage('field name is required'),
]

export const locationValidators = [
  check('latitude').notEmpty().withMessage('field latitude is required'),
  check('longitude').notEmpty().withMessage('field longitude is required'),
]