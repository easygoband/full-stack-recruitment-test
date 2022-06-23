import { IUser, User } from '@models/user.model'
import { ErrorHandler } from '@middlewares/error_handler'
import {PipelineStage} from 'mongoose'

export class AuthService {

  async signup(user: IUser) {    
    const dbUser = await User.findOne({ name: user.name })
    
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

  async userInfected( survivorId: string, userId: string  ) {
    let success = false;
    const conditions = {
      _id: survivorId,
      'reports._id': { $ne: userId }
    };
    const update = {
      $addToSet: { reports: { _id: userId} },
    }

    const userInfo = await User.findOneAndUpdate(conditions, update, {new: true});

    if( userInfo && (userInfo.reports.length > 2) ){
      userInfo.infected = true;
      userInfo.save()
      success = true;
    }

    return {success};
  }

  async usersReports( ) {
    const userList = await User.find( {} );
    let report = {
      users: {
        points: 0,
        total: 0,
        water: 0,
        food: 0,
        medication: 0,
        ammunition: 0,
      },
      infected: {
        points: 0,
        total: 0,
        water: 0,
        food: 0,
        medication: 0,
        ammunition: 0,
      },
    }

    userList.map( item => {
      item.points();
      if(!item.infected){
        report.users.total ++;
        report.users.points += item.itemsPoints;
        report.users.water += item.items.water;
        report.users.food += item.items.food;
        report.users.medication += item.items.medication;
        report.users.ammunition += item.items.ammunition;
      }else{
        report.infected.total ++;
        report.infected.points += item.itemsPoints;
        report.infected.water += item.items.water;
        report.infected.food += item.items.food;
        report.infected.medication += item.items.medication;
        report.infected.ammunition += item.items.ammunition;
      }
    })
    return report;
  }

}
