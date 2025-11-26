import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from
  '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Auth } from '../auth';

@Component({
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
})
export class Login {

  form: FormGroup; // declarar sin inicializar con this.fb
  msg: string | null = null;
  constructor(
    private fb: FormBuilder,
    private auth: Auth,
    private router: Router,
    private route: ActivatedRoute
  ) {
//
    this.form = this.fb.group({
      username: ['Admin', [Validators.required]],
      password: ['123', [Validators.required, Validators.minLength(3)]],
    });
    this.route.queryParamMap.subscribe((map) => {
      this.msg = map.get('msg');
    });
  }
  onSubmit() {
    const { username, password } = this.form.value;
    const ok = this.auth.login(username!, password!);
    if (ok) {
      this.router.navigate(['/home']);
    } else {
      this.router.navigate(['/login'], {
        queryParams: { msg: 'Usuario No valido' },
      });
    }
  }
  // getters para template
  get usernameCtrl() {
    return this.form.get('username');
  }
  get passwordCtrl() {
    return this.form.get('password');
  }
}
