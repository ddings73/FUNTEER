export type FaqElementType = {
    id: number;
    title: string;
    localDate:string
  };

  export type FaqDetailElementType = {
    id: number;
    title: string;
    content:string;
  };


export interface FaqInterface {
    title: string;
    content: string | undefined;
}