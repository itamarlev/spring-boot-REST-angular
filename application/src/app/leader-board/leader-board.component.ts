import { Component, OnInit } from '@angular/core';
import {FormGroup, NgModel} from '@angular/forms';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AnswerResult} from '../../model/answer-result';
import {ServerEndpoint} from '../../lib/server-endpoint.enum';
import {UserScore} from '../../model/user-score';

@Component({
  selector: 'app-leader-board',
  templateUrl: './leader-board.component.html',
  styleUrls: ['./leader-board.component.css']
})
export class LeaderBoardComponent implements OnInit {
  public userScoreObservable$: Observable<UserScore[]>;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  fetchStatistics(game: NgModel) {
    const request = ServerEndpoint.BASE_URL + ServerEndpoint.GET_LEADER_BOARD;
    const params = new HttpParams()
      .set('game', game.value);
    this.userScoreObservable$ = this.http.get<UserScore[]>(request, {params});
  }
}
