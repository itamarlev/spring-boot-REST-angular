import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AnswerResult} from '../model/answer-result';
import {FormGroup} from '@angular/forms';
import {ServerEndpoint} from '../lib/server-endpoint.enum';

@Injectable({
  providedIn: 'root'
})
export class GameManagerService {

  constructor(private http: HttpClient) {
  }

  public answerQuestion(playerAnswer: FormGroup): Observable<AnswerResult> {
    const request = ServerEndpoint.BASE_URL + ServerEndpoint.ANSWER_QUESTION;
    const params: HttpParams = new HttpParams();
    params.set('name', playerAnswer.get('userName').value);
    params.set('game', playerAnswer.get('gameNumber').value);
    params.set('question', playerAnswer.get('questionId').value);
    params.set('answer', playerAnswer.get('answerId').value);
    return this.http.get<AnswerResult>(request, {params});
  }

}
