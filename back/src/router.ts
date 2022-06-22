import { Router } from 'express'
import AuthRoutes from './modules/auth/auth.routes';
const router = Router()

//importing all routes here
router.get('/', (req, res) => {
    return res.json({ hello: 'Wordl' });
})
router.use( AuthRoutes );

export default router
