import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm} from '@angular/forms';
import {PlayerAnswerMove} from '../../model/player-answer-move';
import {Observable} from 'rxjs';
import {AnswerResult} from '../../model/answer-result';
import {GameManagerService} from '../../services/game-manager.service';
import {HttpClient} from '@angular/common/http';
import {ServerEndpoint} from '../../lib/server-endpoint.enum';

@Component({
  selector: 'app-answer-questions',
  templateUrl: './answer-questions.component.html',
  styleUrls: ['./answer-questions.component.css']
})
export class AnswerQuestionsComponent implements OnInit {
  @ViewChild('formGroup') formGroup: NgForm;
  playerAnswerMove: PlayerAnswerMove;
  public answerResultObservable$: Observable<AnswerResult>;

  constructor(private http: HttpClient) {
    this.playerAnswerMove = new PlayerAnswerMove();
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const request = ServerEndpoint.BASE_URL + ServerEndpoint.ANSWER_QUESTION;
    this.playerAnswerMove.answerId = this.formGroup.form.controls.answerId.value;
    this.playerAnswerMove.questionId = this.formGroup.form.controls.questionId.value;
    this.playerAnswerMove.userName = this.formGroup.form.controls.userName.value;
    this.playerAnswerMove.gameNumber = this.formGroup.form.controls.gameNumber.value;

    this.answerResultObservable$ = this.http.post<AnswerResult>(request, this.playerAnswerMove);
  }

  onReset() {
    this.formGroup.reset();

  }
}
