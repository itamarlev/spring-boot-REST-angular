import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import { LeaderBoardComponent } from './leader-board/leader-board.component';
import { AnswerQuestionsComponent } from './answer-questions/answer-questions.component';
import { HomeComponent } from './home/home.component';

const appRoutes: Routes =  [
  {path: '', component: HomeComponent},
  {path: 'answer', component: AnswerQuestionsComponent},
  {path: 'leader-board', component: LeaderBoardComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LeaderBoardComponent,
    AnswerQuestionsComponent,
    HomeComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
