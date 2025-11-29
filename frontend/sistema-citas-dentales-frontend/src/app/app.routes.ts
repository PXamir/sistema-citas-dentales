import { Routes } from '@angular/router';

import { Login } from './login/login';
import { Home } from './home/home';

import { AuthGuard } from './auth-guard';

import { Register } from './register/register';
import{ Citas } from './citas/citas';
import{ AgendarCita } from './agendar-cita/agendar-cita';
import{ MisCitas } from './mis-citas/mis-citas';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: Login },
    { path: 'home', component: Home, canActivate: [AuthGuard] },

    {path: 'citas', component: Citas, canActivate: [AuthGuard]},

    { path: 'register', component: Register },
    { path: 'agendar', component: AgendarCita, canActivate: [AuthGuard] },
    {path: 'mis-citas', component: MisCitas, canActivate: [AuthGuard]},


    { path: '**', redirectTo: 'login' }
];
