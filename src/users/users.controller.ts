import {Body, Controller, Get, Post} from '@nestjs/common';
import {UsersService} from "./users.service";

@Controller('auth')
export class UsersController {
    constructor(private usersService: UsersService) {}

    @Post('register')
    findAll(@Body() body:any){
        return this.usersService.register(body) ;
    }

    @Post('login')
    login(@Body() body:any){
        return this.usersService.login(body.email, body.password);
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
