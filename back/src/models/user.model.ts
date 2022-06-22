import mongoose, { Document } from 'mongoose'
import { mongo } from '@database/database'
import bcrypt from 'bcrypt'
import { settings } from '@config/settings'
import jwt from 'jsonwebtoken'

export interface IUser extends Document {
  name: string
  age: number
  gender: string
  location: Map<string, string>
  items: Map<string, string>
  created_at: Date
  updated_at: Date
  encryptPassword(password: string): string
  comparePassword(password: string): boolean
  createToken(): string
}

const UserSchema = new mongoose.Schema<IUser>({
  age: { type: Number, required: true },
  name: { type: String, required: true , trim: true},
  gender: { type: String, required: true },
  location: {  type: Map,  of: String },
  items: { type: Map,  of: Number , required: true},
  created_at: { type: Date, default: new Date() },
  updated_at: { type: Date, default: new Date() },
})

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

mongoose.connect(settings.DB.URI);

export const User = mongoose.model<IUser>('User', UserSchema)
