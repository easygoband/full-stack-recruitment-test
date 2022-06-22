import { IUser, User } from '@models/user.model'
import { ErrorHandler } from '@middlewares/error_handler'
import mongoose from 'mongoose'

export class AuthService {

  async signup(user: IUser) {    
    const dbUser = await User.findOne({ email: user.name })

    if (dbUser) throw new ErrorHandler(400, 'Name already exists')

    const newUser = new User(user)

    await newUser.save()
    const token = newUser.createToken()

    return { user: newUser, token }
  }

  async login(name: string) {
    const dbUser = await User.findOne({ name })
    if (!dbUser) throw new ErrorHandler(400, 'Bad credentials')
    // const match = dbUser.comparePassword(password)
    // if (!match) throw new ErrorHandler(400, 'Bad credentials')
    const token = dbUser.createToken()
    return { user: dbUser, token }
  }

  async udpateLocation( latitude: string, longitude: string, userId: string ) {
    
    const dbUser = await User.findOneAndUpdate({ _id: userId }, { location: { latitude, longitude } })
    console.log(dbUser);
    return { success: true  }
  }

}
