import { NextFunction, Request, Response } from 'express'
import { AuthService } from './services'


export async function signup(req: Request, res: Response, next: NextFunction): Promise<void> {
  const authService = new AuthService()

  const body = req.body

  try {
    const user = await authService.signup(body)
    res.json(user)
  } catch (error) {
    next(error)
  }
}

export async function login(req: Request, res: Response, next: NextFunction): Promise<void> {
  const authService = new AuthService()

  const body = req.body

  try {
    const user = await authService.login(body.name)
    res.json(user)
  } catch (error) {
    next(error)
  }
}


export async function udpateLocation(req: Request, res: Response, next: NextFunction): Promise<void> {
  const authService = new AuthService()

  const { latitude, longitude} = req.body;
  // const { _id } = req.user ;

  try {
    const user = await authService.udpateLocation( latitude, longitude , req.user._id  )
    res.json(user)
  } catch (error) {
    next(error)
  }
}

