export class AnswerResult {
  status: Status;
  pointsEarned: number;
  errorMessage?: string;
}

export enum Status {
  CORRECT,
  WRONG,
  ILLEGAL
}

