import React from 'react'
import { IconButton } from '@mui/material';
import AddCircleOutlineOutlinedIcon from '@mui/icons-material/AddCircleOutlineOutlined';
import RemoveCircleOutlineRoundedIcon from '@mui/icons-material/RemoveCircleOutlineRounded';
import styles from './TabContent.module.scss'
import { amountLevelType } from '../../types/funding';
import { stringToSeparator } from '../../utils/convert';

type TabContentPropsType = {
    data: amountLevelType;
    minAmount:string
    onChangeTextHandler: (e: React.ChangeEvent<HTMLInputElement>) => void;
    onChangeTodoHandler: (e: React.ChangeEvent<HTMLInputElement>) => void;
    onKeyDownHandler: (e: React.KeyboardEvent<HTMLInputElement>,level:string) => void;
    addTodos: (level: string) => void;
    removeTodo:(remove:number,level:string)=>void
    todoText:string
    level: string;
  };
  function TabContent(props: TabContentPropsType) {
    const {minAmount, data, onChangeTextHandler, onChangeTodoHandler, onKeyDownHandler, addTodos, level,todoText ,removeTodo} = props;
  
    console.log(data)
    const onClickAddTodos = ()=>{
      console.log(todoText);
      
      if(todoText.length > 0)
      addTodos(level)
    }

    const onClickRemovetTodo = (remove:number)=>{
      removeTodo(remove,level)
    }
  
    const onKeydownAddTodos = (e:React.KeyboardEvent<HTMLInputElement>)=>{
      if(e.key === "Enter" && todoText.length > 0 )
      onKeyDownHandler(e,level)
    }
    return (
      <div className={styles['stage-contents-box']}>
        <div className={styles.stage}>
          <p className={styles['stage-title']}>목표 금액 <span>{stringToSeparator(String(minAmount))}원 이상 입력해주세요.</span></p>
          <input
            type="text"
            value={data.amount}
            placeholder="펀딩 최소달성 금액을 입력해주세요"
            name={level}
            className={styles['input-text']}
            onChange={onChangeTextHandler}
          />
  
          <div className={styles['todo-list-box']}>
            <p className={styles['todo-list-title']}>기준 충족시 진행할 봉사의 내용을 입력해주세요.</p>
            {data.descriptions.map((todo, index) => (
              // eslint-disable-next-line react/no-array-index-key
              <p key={index} className={styles['todo-contents']}>
                {index + 1}. {todo.description} <IconButton onClick={()=>onClickRemovetTodo(index)} sx={{color:"rgba(211, 79, 4, 1)"}}><RemoveCircleOutlineRoundedIcon color='inherit'/></IconButton>
              </p>
            ))}
  
            <div className={styles['todo-input-box']}>
              <input
                type="text"
                className={styles['input-text']}
                value={todoText}
                placeholder="내용을 입력해주세요."
                onChange={onChangeTodoHandler}
                onKeyDown={onKeydownAddTodos}
              />
              <IconButton size="large" aria-label="delete" className={styles['create-button']} color="warning" onClick={onClickAddTodos}>
                <AddCircleOutlineOutlinedIcon fontSize="inherit" />
              </IconButton>
            </div>
          </div>
        </div>
      </div>
    );
  }

  export default TabContent