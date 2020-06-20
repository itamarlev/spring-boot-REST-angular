import {AnswerResult} from './answer-result';

export class PlayerAnswerMove {
  userName: string;
  gameNumber: number;
  questionId: number;
  answerId: number;
  answerResult?: AnswerResult;
}
