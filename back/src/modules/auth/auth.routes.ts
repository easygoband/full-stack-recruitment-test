import { Router } from 'express'
import * as Controller from './auth.controller'
import { loginValidators, signupValidators, locationValidators } from './auth.validator'
import { validateBody } from '@middlewares/validator'
import { Auth } from '@middlewares/passport'

const router = Router()

router.post('/signup', [...signupValidators, validateBody], Controller.signup)
//
router.post('/login', [...loginValidators, validateBody], Controller.login)

router.get('/users', Controller.userList)

router.get('/users/:userId', Controller.userInfo)

router.put('/user/location', Auth.authenticate , [...locationValidators, validateBody]  , Controller.udpateLocation)

router.put('/users/reports' , [...locationValidators, validateBody]  , Controller.userReports)

router.post('/user/infected/:userId', Auth.authenticate, Controller.userInfected)


export default router
