import {Controller, Get, Post} from '@nestjs/common';

@Controller('auth')
export class UsersController {

    @Post('register')
    findAll():string{
        return 'register';
    }

    @Post('login')
    login():string{
        return 'login';
    }

    @Post('logout')
    logout():string{
        return 'logout';
    }

    @Get('profile')
    getProfile():string{
        return 'profile';
    }
}
