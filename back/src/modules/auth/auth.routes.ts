import { Router } from 'express'
import * as Controller from './auth.controller'
import { loginValidators, signupValidators, locationValidators } from './auth.validator'
import { validateBody } from '@middlewares/validator'
import { Auth } from '@middlewares/passport'

const router = Router()

router.post('/signup', [...signupValidators, validateBody], Controller.signup)
//
router.post('/login', [...loginValidators, validateBody], Controller.login)

router.post('/user/location', Auth.authenticate , [...locationValidators, validateBody]  , Controller.udpateLocation)


export default router
