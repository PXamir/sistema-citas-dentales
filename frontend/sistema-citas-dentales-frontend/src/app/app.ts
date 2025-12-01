import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,                // <-- obligatorio con bootstrapApplication
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrls: ['./app.css']         // <-- nota: plural "styleUrls"
})
export class App {}
