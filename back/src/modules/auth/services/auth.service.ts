import { IUser, User } from '@models/user.model'
import { ErrorHandler } from '@middlewares/error_handler'
import mongoose from 'mongoose'

export class AuthService {

  async signup(user: IUser) {    
    const dbUser = await User.findOne({ name: user.name })
    console.log( dbUser );
    
    if (dbUser) throw new ErrorHandler(400, 'Name already exists')

    const newUser = new User(user)

    await newUser.save()
    const token = newUser.createToken()
    
    newUser?.points();
    return { user: newUser, token }
  }

  async login(name: string) {
    const dbUser = await User.findOne({ name })
    if (!dbUser) throw new ErrorHandler(400, 'Bad credentials')
    // const match = dbUser.comparePassword(password)
    // if (!match) throw new ErrorHandler(400, 'Bad credentials')
    dbUser?.points();
    const token = dbUser.createToken()
    return { user: dbUser, token }
  }

  async udpateLocation( latitude: string, longitude: string, userId: string ) {
    const dbUser = await User.findOneAndUpdate({ _id: userId }, { location: { latitude, longitude } })
    return { success: true  }
  }

  async userList( userId: any ) {
    let criteria = {};
    if( userId ){
      criteria = {
        _id: { $ne: userId }
      }
    }
    const dbUserList = await User.find( criteria );
    if(dbUserList && dbUserList.length > 0 ){
      dbUserList.map( item => item.points() );
    }
    return dbUserList;
  }

  async userInfo( userId: string ) {
    
    const userInfo = await User.findOne({ _id: userId } );
    userInfo?.points();
    return userInfo;
  }

}
