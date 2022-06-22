import { Strategy, ExtractJwt, StrategyOptions } from 'passport-jwt';
import { settings } from '@config/settings';
import { User } from '@models/user.model';
import passport from 'passport';

export const JWTStrategy = new Strategy(
  {
    jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
    secretOrKey: settings.SECRET,
  },
  async (payload, done) => {
    try {
      console.log('JWTStrategy', payload);

      const user = await User.findOne({ _id: payload._id})
      console.log( user );
      
      if (user) return done(null, user)

      return done(null, false)
    } catch (error) {
      done(error, false)
    }
  }
)
passport.use( JWTStrategy )

export const Auth = {
  initialize: function () {
      return passport.initialize();
  },
  authenticate: function (req, res, next) {
      return passport.authenticate("jwt", {
          session: false
      }, (err, user, info) => {
        console.log('Middleware ');
        
          if (err) {
              console.log(err);
              return next(err);
          }
          if (!user) {
              return res.json({
                  status: 'error',
                  error: 'ANOTHORIZED_USER'
              });
          }
          // Forward user information to the next middleware
          req.user = user; 
          next();
      })(req, res, next);
  }
};
