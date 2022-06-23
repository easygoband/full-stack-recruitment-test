import mongoose, { Document } from 'mongoose'
import { mongo } from '@database/database'
import bcrypt from 'bcrypt'
import { settings } from '@config/settings'
import jwt from 'jsonwebtoken'

export interface IUser extends Document {
  name: string
  age: number
  gender: string
  infected: boolean
  location: Map<string, string>
  items: IItems
  created_at: Date
  updated_at: Date
  itemsPoints: number
  reports: IUserIds[]
  encryptPassword(password: string): string
  points(): void
  comparePassword(password: string): boolean
  createToken(): string
}

export interface ILocation extends Document {
  latitude: string,
  longitude: string
}

export interface IItems extends Document {
  water: number,
  food: number,
  medication: number,
  ammunition: number
}
export interface IUserIds extends Document {
  user: string,
  _id: string,
}

const LocationSchema = new mongoose.Schema<ILocation>({
  latitude: { type: String, required: true }, 
  longitude: { type: String, required: true }, 
})

const ItemsSchema = new mongoose.Schema<IItems>({
  water: { type: Number, required: true }, 
  food: { type: Number, required: true }, 
  medication: { type: Number, required: true }, 
  ammunition: { type: Number, required: true }, 
})

const UserIdsSchema = new mongoose.Schema<IUserIds>({
  user: { type: String }, 
  _id: { type: String }, 
})

const UserSchema = new mongoose.Schema<IUser>({
  age: { type: Number, required: true },
  itemsPoints: { type: Number },
  name: { type: String, required: true , trim: true},
  gender: { type: String, required: true },
  infected: { type: Boolean, default: false },
  reports: { type: [UserIdsSchema] },
  location: LocationSchema,
  items: ItemsSchema,
  created_at: { type: Date, default: new Date() },
  updated_at: { type: Date, default: new Date() },
},)

UserSchema.methods.toJSON = function () {
  const user: any = this.toObject()
  const { __v, password, ...rest } = user
  return rest
}

UserSchema.methods.createToken = function () {
  const user = this.toObject()

  return jwt.sign({ _id: user._id }, settings.SECRET, {
    expiresIn: 86400,
  })
}

UserSchema.methods.encryptPassword = function (password: string) {
  return bcrypt.hashSync(password, bcrypt.genSaltSync(10))
}

UserSchema.methods.comparePassword = function (password: string) {
  let user = this.toObject()
  return bcrypt.compareSync(password, user.password)
}

UserSchema.methods.points = function () {
  let user = this.toObject();
  let total = 0;
  if(user.items){
    total += user.items.water * 4;
    total += user.items.food * 4;
    total += user.items.medication * 4;
    total += user.items.ammunition * 4;
  }
  this.to
  this.itemsPoints = total;
}

mongoose.connect(settings.DB.URI);

export const User = mongoose.model<IUser>('User', UserSchema)
