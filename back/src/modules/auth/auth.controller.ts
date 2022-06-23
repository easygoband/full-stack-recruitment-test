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

  try {
    const user = await authService.udpateLocation( latitude, longitude , req.user._id  )
    res.json(user)
  } catch (error) {
    next(error)
  }
}

export async function userList(req: Request, res: Response, next: NextFunction): Promise<void> {
  const authService = new AuthService()
 
  try {
    const userList = await authService.userList( req.query.userId  )
    res.json(userList)
  } catch (error) {
    next(error)
  }
}

export async function userInfo(req: Request, res: Response, next: NextFunction): Promise<void> {
  const authService = new AuthService()
 
  try {
    const user = await authService.userInfo( req.params.userId  )
    res.json(user)
  } catch (error) {
    next(error)
  }
}

export async function userInfected(req: Request, res: Response, next: NextFunction): Promise<void> {
  const authService = new AuthService()
 
  try {
    const user = await authService.userInfected( req.params.userId , req.user._id )
    res.json(user)
  } catch (error) {
    next(error)
  }
}
export async function usersReports(req: Request, res: Response, next: NextFunction): Promise<void> {
  const authService = new AuthService()
 
  try {
    const report = await authService.usersReports()
    res.json(report)
  } catch (error) {
    next(error)
  }
}